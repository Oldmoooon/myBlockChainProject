package http;

import cn.hutool.core.io.FileUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import dao.IpfsDao;
import dao.RedisDao;
import com.alibaba.fastjson.JSON;
import contract.ContractManager;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.ThymeleafTemplateEngine;
import model.OfficialDocument;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.File;
import java.math.BigInteger;

/**
 * @author guyue
 * @date 2018/4/25
 */
public class AddDocumentHandler implements IHttpHandler {
    private static Log log = LogFactory.get();

    @Override
    public void run(RoutingContext routingContext) {
        HttpServerRequest request = routingContext.request();
        log.info("add document post request is {}", JSON.toJSONString(request));
        String printNo = request.getParam("printNo");
        String secretLevel = request.getParam("secretLevel");
        String secretData = request.getParam("secretData");
        String emergency = request.getParam("emergency");
        String officialName = request.getParam("officialName");
        String officialMark = request.getParam("officialMark");
        String sender = request.getParam("sender");
        String title = request.getParam("title");
        String receivedAuthority = request.getParam("receivedAuthority");
        String content = request.getParam("content");
        String enclosureInfo = request.getParam("enclosureInfo");
        String senderPublicKey = request.getParam("senderPublicKey");
        String createDate = request.getParam("createDate");
        String officialPublicKey = request.getParam("officialPublicKey");
        String notes = request.getParam("notes");
        String copyToAuthority = request.getParam("copyToAuthority");
        String printAuthority = request.getParam("printAuthority");
        String printDate = request.getParam("printDate");
        if ((officialName == null) || (officialMark == null) || (sender == null) || (title == null)
                || (receivedAuthority == null) || (content == null) || (senderPublicKey == null)
                || (createDate == null) || (officialPublicKey == null)) {
            routingContext.response().end("参数错误");
            return;
        }
        OfficialDocument document = new OfficialDocument(officialName, officialMark, sender, title,
                receivedAuthority, content, senderPublicKey, createDate, officialPublicKey);
        document.setPrintNo(printNo);
        document.setSecretLevel(secretLevel);
        document.setSecretData(secretData);
        document.setEmergency(emergency);
        document.setEnclosureInfo(enclosureInfo);
        document.setNotes(notes);
        document.setCopyToAuthority(copyToAuthority);
        document.setPrintAuthority(printAuthority);
        document.setPrintDate(printDate);

        FileUpload fileUpload = routingContext.fileUploads().iterator().next();
        log.debug("file up loaded, file upload file name is {}, file name is {}, name is {}.",fileUpload.uploadedFileName(), fileUpload.fileName(), fileUpload.name());
        File file = new File(fileUpload.uploadedFileName());
        log.debug("file {} up load size is {}.", file.getAbsolutePath(), file.length());
        if (file.length() > 0) {
            String save = IpfsDao.save(document.getOfficialMark(), FileUtil.readBytes(file));
            document.setEnclosure(save);
        } else {
            document.setEnclosure("");
        }
        FileUtil.del(file);

        String key = String.valueOf(document.hashCode());
        String documentJson = JSON.toJSONString(document);
        routingContext.vertx().executeBlocking(future -> {
            try {
                TransactionReceipt send = ContractManager.getContract().setDocument(key, documentJson, BigInteger.ZERO, BigInteger.ZERO).send();
                log.debug("document official name {} transaction receipt is {}, document key is {}, enclosure base58 is {}.", officialName, send, key, document.getEnclosure());
                try {
                    RedisDao.getRedis().sadd(document.getOfficialMark(), key);
                } catch (Exception e) {
                    log.error(e, "save document official mark {} in redis error, key(hashcode) is {}.", document.getOfficialMark(), key);
                }
                log.debug("save document official mark {} in redis success, key(hashcode) is {}.", document.getOfficialMark(), key);
                future.complete(send);
            } catch (Exception e) {
                log.error(e, "try send contract set command error.");
                future.fail(e);
            }
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                ThymeleafTemplateEngine templateEngine = ThymeleafTemplateEngine.create();
                routingContext.put("msg", "success save a document on blockchain.");
                routingContext.put("entries", JSON.parseObject(documentJson).entrySet());
                templateEngine.render(routingContext, "templates/", "result.html", bufferAsyncResult -> {
                    if (bufferAsyncResult.succeeded()) {
                        routingContext.response()
                                .putHeader("content-type", "Content-Type: text/html; charset=utf-8")
                                .end(bufferAsyncResult.result());
                    } else {
                        routingContext.fail(bufferAsyncResult.cause());
                    }
                });
            } else {
                routingContext.fail(asyncResult.cause());
            }
        });
    }
}

package me.monaparty.countertable.ipfs;

import java.util.List;

import org.bson.Document;
import org.bson.json.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import me.monaparty.api.CounterpartyAPI;
import me.monaparty.api.Filter;
import me.monaparty.api.GetIssuancesResult;

@Component
public final class IPFSPinner {
    public static final Logger logger = LoggerFactory.getLogger(IPFSPinner.class);

    private long offset = 0;

    @Autowired
    private CounterpartyAPI counterpartyAPI;

    @Autowired
    private IPFSService ipfsService;

    @Scheduled(cron = "0 * * * * *")
    public void cron() {
        logger.info("New Content scan is triggered.");
        final List<GetIssuancesResult> results = counterpartyAPI.getIssuances(
                new Filter[] { new Filter("description", "LIKE", "{%") },
                "AND", null, null, null, null, "valid", 1000L,
                offset);
        offset += results.size();
        for (final GetIssuancesResult result : results) {
            try {
                final Document doc = Document.parse(result.description);
                final String cid = doc.get("monacard", Document.class).getString("cid");
                ipfsService.addPin(cid);
            } catch (final JsonParseException | NullPointerException e) {
                logger.debug("Can't parse description: "
                    + (result.description != null ? result.description : "(null)"));
            }
        }
        logger.info("New Content scan is done.");
    }
}
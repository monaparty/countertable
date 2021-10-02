package me.monaparty.countertable.ipfs;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.ipfs.api.IPFS;
import io.ipfs.cid.Cid;
import io.ipfs.multihash.Multihash;

@Component
public class IPFSService {
    private static final Logger logger = LoggerFactory.getLogger(IPFSService.class);

    @Value("${ipfs.api.multiaddr}")
    private String addr;

    @PostConstruct
    void connect() {
    }

    void addPin(final String cid) {
        try {
            IPFS ipfs = new IPFS(addr);
            ipfs.pin.add((Multihash) Cid.decode(cid));
            logger.info("Pinned. cid = " + cid);
        } catch (final IOException e) {
            logger.debug("Can't pin. cid = " + cid, e);
        }
    }
}
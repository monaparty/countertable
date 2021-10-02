package me.monaparty.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetIssuancesResult {
    public Long tx_index;
    public Long msg_index;
    public Long fee_paid;
    public Long call_price;
    public Long fungible;
    public Long vendable;
    public String description;
    public Long reassignable;
    public String source;
    public Long divisible;
    public String issuer;
    public Long quantity;
    public String asset;
    public String tx_hash;
    public Long block_index;
    public Long call_date;
    public String status;
    public Long callable;
    public Long transfer;
    public Long listed;
    public Long locked;
    public String asset_longname;
}
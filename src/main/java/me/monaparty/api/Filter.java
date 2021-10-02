package me.monaparty.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Filter {
    public String field;
    public String op;
    public String value;

    public Filter(String field, String op, String value) {
        this.field = field;
        this.op = op;
        this.value = value;
    }
}


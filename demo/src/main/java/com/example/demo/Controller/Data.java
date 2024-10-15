package com.example.demo.Controller;

import java.time.Instant;

class Data {
    public long seqNo;
    public Instant timestamp;

    public Data(long seqNo, Instant timestamp){
        this.seqNo = seqNo;
        this.timestamp = timestamp;
    }

}

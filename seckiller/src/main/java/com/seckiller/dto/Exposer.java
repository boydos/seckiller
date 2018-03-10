package com.seckiller.dto;

/**
 * 暴露秒杀接口DTO
 */
public class Exposer {

    private long seckillerId;

    private boolean enable;

    private String md5;

    private long now;

    private long start;

    private long end;

    public Exposer(long seckillerId, boolean enable) {
        this.seckillerId = seckillerId;
        this.enable = enable;
    }

    public Exposer(long seckillerId,boolean enable, long now, long start, long end) {
        this.seckillerId = seckillerId;
        this.enable = enable;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(long seckillerId, boolean enable, String md5) {
        this.seckillerId = seckillerId;
        this.enable = enable;
        this.md5 = md5;
    }

    public long getSeckillerId() {
        return seckillerId;
    }

    public void setSeckillerId(long seckillerId) {
        this.seckillerId = seckillerId;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "{" +
                "seckillerId:" + seckillerId +
                ", enable:" + enable +
                ", md5:'" + md5 + '\'' +
                ", now:" + now +
                ", start:" + start +
                ", end:" + end +
                '}';
    }
}

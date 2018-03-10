package com.seckiller.dto;

/**
 * 暴露秒杀接口DTO
 */
public class Exposer {

    private boolean expose;

    private long seckillerId;

    private String md5;

    private long now;

    private long start;


    private long end;

    public Exposer(boolean expose, long seckillerId, String md5) {
        this.expose = expose;
        this.seckillerId = seckillerId;
        this.md5 = md5;
    }

    public Exposer(boolean expose, long seckillerId, long now, long start, long end) {
        this.expose = expose;
        this.seckillerId = seckillerId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean expose, long seckillerId) {
        this.expose = expose;
        this.seckillerId = seckillerId;
    }

    public long getSeckillerId() {
        return seckillerId;
    }

    public void setSeckillerId(long seckillerId) {
        this.seckillerId = seckillerId;
    }


    public boolean isExpose() {
        return expose;
    }

    public void setExpose(boolean expose) {
        this.expose = expose;
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
        return "Exposer{" +
                "expose=" + expose +
                ", seckillerId=" + seckillerId +
                ", md5='" + md5 + '\'' +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}

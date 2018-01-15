package com.yto.template.module.bean;

/**
 * Created by Chris on 2017/11/30.
 */

public class InitBean {

    private String compression_ratio;//图片压缩率
    private String photo_size;
    private String collect_interval;//收集周期
    private String pack_interval;//打包周期
    private String fence_flow_lock;// 是否限制在围栏内才能签到;0 不限制; 1限制
    private String fence_status_lock;//是否根据围栏触发记录帮助司机自动改变运单状态 ; 0 否  1是;
    private String navi_lock;//定位权限锁 //是否强制用户授予定位权限; 暂时用不到

    private String speed_collect_interval;//速度采集时间，参考值 2秒
    private String report_off_speed_gather_number;//异常关闭速度采集点总数，60个
    private String average_speed;//平均速度，参考值 0KM/H
    private String draft_check_interval;//草稿箱检查周期，5分钟
    private String msg_expired_time;//消息过期时间，30分钟
    private String comments_url;//意见反馈url
    private String qq_group_num;//qq群号
    private String qq_group_ios_key;//qq key ios
    private String qq_group_android_key;//qq key android
    private String report_speed_2000;//堵车
    private String report_speed_2100;//异常天气
    private String report_speed_2800;//缓行
    public String page_size;//分页大小

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getCompression_ratio() {
        return compression_ratio;
    }

    public void setCompression_ratio(String compression_ratio) {
        this.compression_ratio = compression_ratio;
    }

    public String getPhoto_size() {
        return photo_size;
    }

    public void setPhoto_size(String photo_size) {
        this.photo_size = photo_size;
    }

    public String getCollect_interval() {
        return collect_interval;
    }

    public void setCollect_interval(String collect_interval) {
        this.collect_interval = collect_interval;
    }

    public String getPack_interval() {
        return pack_interval;
    }

    public void setPack_interval(String pack_interval) {
        this.pack_interval = pack_interval;
    }

    public String getFence_flow_lock() {
        return fence_flow_lock;
    }

    public void setFence_flow_lock(String fence_flow_lock) {
        this.fence_flow_lock = fence_flow_lock;
    }

    public String getFence_status_lock() {
        return fence_status_lock;
    }

    public void setFence_status_lock(String fence_status_lock) {
        this.fence_status_lock = fence_status_lock;
    }

    public String getNavi_lock() {
        return navi_lock;
    }

    public void setNavi_lock(String navi_lock) {
        this.navi_lock = navi_lock;
    }

    public String getSpeed_collect_interval() {
        return speed_collect_interval;
    }

    public void setSpeed_collect_interval(String speed_collect_interval) {
        this.speed_collect_interval = speed_collect_interval;
    }

    public String getReport_off_speed_gather_number() {
        return report_off_speed_gather_number;
    }

    public void setReport_off_speed_gather_number(String report_off_speed_gather_number) {
        this.report_off_speed_gather_number = report_off_speed_gather_number;
    }

    public String getAverage_speed() {
        return average_speed;
    }

    public void setAverage_speed(String average_speed) {
        this.average_speed = average_speed;
    }

    public String getDraft_check_interval() {
        return draft_check_interval;
    }

    public void setDraft_check_interval(String draft_check_interval) {
        this.draft_check_interval = draft_check_interval;
    }

    public String getMsg_expired_time() {
        return msg_expired_time;
    }

    public void setMsg_expired_time(String msg_expired_time) {
        this.msg_expired_time = msg_expired_time;
    }

    public String getComments_url() {
        return comments_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public String getQq_group_num() {
        return qq_group_num;
    }

    public void setQq_group_num(String qq_group_num) {
        this.qq_group_num = qq_group_num;
    }

    public String getQq_group_ios_key() {
        return qq_group_ios_key;
    }

    public void setQq_group_ios_key(String qq_group_ios_key) {
        this.qq_group_ios_key = qq_group_ios_key;
    }

    public String getQq_group_android_key() {
        return qq_group_android_key;
    }

    public void setQq_group_android_key(String qq_group_android_key) {
        this.qq_group_android_key = qq_group_android_key;
    }

    public String getReport_speed_2000() {
        return report_speed_2000;
    }

    public void setReport_speed_2000(String report_speed_2000) {
        this.report_speed_2000 = report_speed_2000;
    }

    public String getReport_speed_2100() {
        return report_speed_2100;
    }

    public void setReport_speed_2100(String report_speed_2100) {
        this.report_speed_2100 = report_speed_2100;
    }

    public String getReport_speed_2800() {
        return report_speed_2800;
    }

    public void setReport_speed_2800(String report_speed_2800) {
        this.report_speed_2800 = report_speed_2800;
    }


}

package com.demo.makeview.view.bean;

import java.util.List;

/**
 * 描述:
 * 创建时间：2019/4/25-6:39 PM
 *
 * @author: yinshuai
 */
public class MakeBean {
    private String imageUrl;
    private String name;
    private String post;
    private List<MakeItemBean> times;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public List<MakeItemBean> getTimes() {
        return times;
    }

    public void setTimes(List<MakeItemBean> times) {
        this.times = times;
    }

    public static class MakeItemBean {
        private String id;
        private boolean isEnabled;
        private String time;
        private boolean isOccupy;
        private String remark;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isEnabled() {
            return isEnabled;
        }

        public void setEnabled(boolean enabled) {
            isEnabled = enabled;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public boolean isOccupy() {
            return isOccupy;
        }

        public void setOccupy(boolean occupy) {
            isOccupy = occupy;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }


}



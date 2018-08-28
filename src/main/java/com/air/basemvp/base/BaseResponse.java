package com.air.basemvp.base;

/**
 * @desc 服务器响应结果
 * @author Hexl
 * @date
 */
public class BaseResponse<R> {
    /**
     * response : {"message":"成功","content":{},"code":"0000"}
     * service : /airtimes/airdp/mobile/vdt/task/assault/doDifferentiationAnalysis
     * timestamp : 1520586648652
     */
    private ResponseBean<R> response;
    private String service;
    private long timestamp;

    public ResponseBean<R> getResponse() {
        return response;
    }

    public void setResponse(ResponseBean<R> response) {
        this.response = response;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static class ResponseBean<R> {
        /**
         * message : 成功
         * content : {}
         * code : 0000
         */
        private String message;
        private R content;
        private String code;

        /**
         * 数据成功
         *
         * @return
         */
        public boolean isSucceeded() {
            return code.equals("0000");
        }

        /**
         * 验证token
         * @return true为有效,false为失效.需发送广播
         */
        public boolean isToken() {
            return code.equals("0201");
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public R getContent() {
            return content;
        }

        public void setContent(R content) {
            this.content = content;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}

package com.imranslab.module4;

public class ThreadWaitNotifyAndNotifyAll {
    static class Message{
        private String msg;

        public Message(String str)
        {
            this.msg = str;
        }

        public String getMessage()
        {
            return msg;
        }

        public void setMessage(String str)
        {
            this.msg = str;
        }

    }
}

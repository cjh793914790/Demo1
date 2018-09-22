package com.example.jahaocao.demo1.data;

import java.util.List;

public class Comment {

        /**
         * commentList : [{"commentTime":"2018-09-18 17:11:59","content":"测试","headImagePath":"http://39.107.224.233/firstga/imagesnull","nickname":null,"userId":"d56ea66e7ee741f498ca51242c8c6394"},{"commentTime":"2018-09-18 16:03:38","content":"潘阳哥哥，我错了","headImagePath":"http://39.107.224.233/firstga/imagesnull","nickname":null,"userId":"d56ea66e7ee741f498ca51242c8c6394"},{"commentTime":"2018-09-18 15:50:18","content":"潘阳大傻逼","headImagePath":"http://39.107.224.233/firstga/imagesnull","nickname":null,"userId":"d56ea66e7ee741f498ca51242c8c6394"},{"commentTime":"2018-09-18 15:01:03","content":"哈哈哈哈哈","headImagePath":"http://39.107.224.233/firstga/imagesnull","nickname":null,"userId":"d56ea66e7ee741f498ca51242c8c6394"},{"commentTime":"2018-09-18 11:41:59","content":"按时发生大发","headImagePath":"http://39.107.224.233/firstga/imagesnull","nickname":null,"userId":"d56ea66e7ee741f498ca51242c8c6394"},{"commentTime":"2018-09-18 11:40:15","content":"阿打算发送到发送到","headImagePath":"http://39.107.224.233/firstga/imagesnull","nickname":null,"userId":"d56ea66e7ee741f498ca51242c8c6394"},{"commentTime":"2018-09-18 09:03:33","content":"测试","headImagePath":"http://39.107.224.233/firstga/imagesnull","nickname":null,"userId":"d56ea66e7ee741f498ca51242c8c6394"},{"commentTime":"2018-09-18 08:48:15","content":"测试","headImagePath":"http://39.107.224.233/firstga/imagesnull","nickname":null,"userId":"d56ea66e7ee741f498ca51242c8c6394"},{"commentTime":"2018-09-18 08:40:44","content":"测试","headImagePath":"http://39.107.224.233/firstga/imagesnull","nickname":null,"userId":"d56ea66e7ee741f498ca51242c8c6394"},{"commentTime":"2018-09-17 18:26:06","content":"测试","headImagePath":"http://39.107.224.233/firstga/imagesnull","nickname":null,"userId":"d56ea66e7ee741f498ca51242c8c6394"}]
         * cursor : 2
         */

        private String cursor;
        private List<CommentListBean> commentList;

        public String getCursor() {
            return cursor;
        }

        public void setCursor(String cursor) {
            this.cursor = cursor;
        }

        public List<CommentListBean> getCommentList() {
            return commentList;
        }

        public void setCommentList(List<CommentListBean> commentList) {
            this.commentList = commentList;
        }

        public static class CommentListBean {
            /**
             * commentTime : 2018-09-18 17:11:59
             * content : 测试
             * headImagePath : http://39.107.224.233/firstga/imagesnull
             * nickname : null
             * userId : d56ea66e7ee741f498ca51242c8c6394
             */

            private String commentTime;
            private String content;
            private String headImagePath;
            private Object nickname;
            private String userId;

            public String getCommentTime() {
                return commentTime;
            }

            public void setCommentTime(String commentTime) {
                this.commentTime = commentTime;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getHeadImagePath() {
                return headImagePath;
            }

            public void setHeadImagePath(String headImagePath) {
                this.headImagePath = headImagePath;
            }

            public Object getNickname() {
                return nickname;
            }

            public void setNickname(Object nickname) {
                this.nickname = nickname;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }

}

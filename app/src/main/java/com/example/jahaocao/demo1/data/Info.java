package com.example.jahaocao.demo1.data;

public class Info {


        /**
         * comments : 20
         * content : <td id="article_content">

         <div><p style="text-align: center;"><a href="http://www.lyunx.com/data/attachment/portal/201805/21/100324trt2d75r0m2l6g61.jpg" target="_blank"><img data-bd-imgshare-binded="1" src="http://www.lyunx.com/data/attachment/portal/201805/21/100324trt2d75r0m2l6g61.jpg"></a></p></div><div><br></div><div>　　今日下午，有网友发帖称，一架公务机在扬州泰州国际机场训练时冲出跑道，造成该机场大面积延误。发帖网友称，冲出跑道的飞机是星联商务航空的B-8129号G200公务机，不是民航机。从网友上传的视频和照片看，事发时间为15时08分，现场没有爆炸或起火，飞机机身保持完整，机翼有损坏，主起落架已经看不见。</div><div><br></div><div>　　18时30分许，北青报记者从扬州泰州国际机场处获悉，今日下午由于一架商务机冲出跑道，机场跑道出现问题，航班无法正常进出港，随后机场暂时关闭，一些未起飞航班被取消，一些即将进港的航班备降。工作人员表示，事发公务机当时是在训练，机上没有乘客，也没有人受伤。至于机场何时能再次开启，工作人员称目前还不清楚。</div><div><br></div> </td>
         * isFavoured : 0
         * isLiked : 1
         * newsId : b9c1dfdc28de4459947dcc4e74637b2c
         * origin :
         * publishTime : 2018-05-20
         * title : 江苏一机场因公务机训练时冲出跑道暂时关闭 部分航班取消或备降
         */

        private int comments;
        private String content;
        private int isFavoured;
        private int isLiked;
        private String newsId;
        private String origin;
        private String publishTime;
        private String title;

        public int getComments() {
            return comments;
        }

        public void setComments(int comments) {
            this.comments = comments;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getIsFavoured() {
            return isFavoured;
        }

        public void setIsFavoured(int isFavoured) {
            this.isFavoured = isFavoured;
        }

        public int getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(int isLiked) {
            this.isLiked = isLiked;
        }

        public String getNewsId() {
            return newsId;
        }

        public void setNewsId(String newsId) {
            this.newsId = newsId;
        }

        public String getOrigin() {
            return origin;
        }

        public void setOrigin(String origin) {
            this.origin = origin;
        }

        public String getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
}

package gbpassenger.ichinait.com.medicine.netbean;

import java.util.List;

/**
 * Created by DawnOct on 2018/1/30.
 */

public class Detail {
    /**
     * code : 200
     * subject : {"created_time":"2018-01-27T05:28:17.734829Z","crowd":{"crowd_funding":"45000","crowd_progress":"0","crowd_providers":[{"address":"北京","age":20,"name":"张三","pk":1,"telephone":"110"}],"pk":18},"describe":"听说美国有个老者 很厉害对糖尿病","disease_type":"糖尿病","doctor_address":"美国","doctor_type":"01","followers":[{"address":"上海","age":20,"name":"李四","pk":2,"telephone":"120"}],"initiator":{"address":"北京","age":20,"name":"张三","pk":1,"telephone":"110"},"origin_from":"01","pk":19,"praise":0,"task":{"pk":18,"task_deadline":"5天","task_executors":{"address":"山西","age":20,"name":"赵柳","pk":6,"telephone":"155"},"task_outline":"去趟山西探老中医","task_start_time":"2018-01-27T05:28:17.593840Z","task_state":""},"top":{"is_top":true,"top_time":"2018-01-27T05:28:17.656334Z"}}
     * taskProgress : [{"created_time":"2018-01-30T05:01:06.072435Z","des":"今天看到了刘大第5555555555","pk":11,"task":{"pk":18,"task_deadline":"5天","task_executors":{"address":"山西","age":20,"name":"赵柳","pk":6,"telephone":"155"},"task_outline":"去趟山西探老中医","task_start_time":"2018-01-27T05:28:17.593840Z","task_state":""},"task_progress_time":"2018-01-30T05:01:06.072435Z"},{"created_time":"2018-01-30T04:59:01.046153Z","des":"今天看到了刘大第四个444444","pk":10,"task":{"pk":18,"task_deadline":"5天","task_executors":{"address":"山西","age":20,"name":"赵柳","pk":6,"telephone":"155"},"task_outline":"去趟山西探老中医","task_start_time":"2018-01-27T05:28:17.593840Z","task_state":""},"task_progress_time":"2018-01-30T04:59:01.046153Z"},{"created_time":"2018-01-30T03:31:17.775472Z","des":"今天看到了刘大夫,和蔼可亲02020202","path":"/media/image/edit.png","pk":9,"task":{"pk":18,"task_deadline":"5天","task_executors":{"address":"山西","age":20,"name":"赵柳","pk":6,"telephone":"155"},"task_outline":"去趟山西探老中医","task_start_time":"2018-01-27T05:28:17.593840Z","task_state":""},"task_progress_time":"2018-01-30T03:31:17.775472Z"},{"created_time":"2018-01-30T02:13:41.724552Z","des":"今天看到了刘大第四个","path":"/media/image/error.log","pk":8,"task":{"pk":18,"task_deadline":"5天","task_executors":{"address":"山西","age":20,"name":"赵柳","pk":6,"telephone":"155"},"task_outline":"去趟山西探老中医","task_start_time":"2018-01-27T05:28:17.593840Z","task_state":""},"task_progress_time":"2018-01-30T02:13:41.723549Z"},{"created_time":"2018-01-30T02:12:44.335997Z","des":"今天看到了刘大夫,和蔼可亲01","path":"/media/image/d439b6003af33a87de0c137bc65c10385343b569.jpg","pk":7,"task":{"pk":18,"task_deadline":"5天","task_executors":{"address":"山西","age":20,"name":"赵柳","pk":6,"telephone":"155"},"task_outline":"去趟山西探老中医","task_start_time":"2018-01-27T05:28:17.593840Z","task_state":""},"task_progress_time":"2018-01-30T02:12:44.335997Z"}]
     */

    private String code;
    private SubjectBean subject;
    private List<TaskProgressBean> taskProgress;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SubjectBean getSubject() {
        return subject;
    }

    public void setSubject(SubjectBean subject) {
        this.subject = subject;
    }

    public List<TaskProgressBean> getTaskProgress() {
        return taskProgress;
    }

    public void setTaskProgress(List<TaskProgressBean> taskProgress) {
        this.taskProgress = taskProgress;
    }

    public static class SubjectBean {
        /**
         * created_time : 2018-01-27T05:28:17.734829Z
         * crowd : {"crowd_funding":"45000","crowd_progress":"0","crowd_providers":[{"address":"北京","age":20,"name":"张三","pk":1,"telephone":"110"}],"pk":18}
         * describe : 听说美国有个老者 很厉害对糖尿病
         * disease_type : 糖尿病
         * doctor_address : 美国
         * doctor_type : 01
         * followers : [{"address":"上海","age":20,"name":"李四","pk":2,"telephone":"120"}]
         * initiator : {"address":"北京","age":20,"name":"张三","pk":1,"telephone":"110"}
         * origin_from : 01
         * pk : 19
         * praise : 0
         * task : {"pk":18,"task_deadline":"5天","task_executors":{"address":"山西","age":20,"name":"赵柳","pk":6,"telephone":"155"},"task_outline":"去趟山西探老中医","task_start_time":"2018-01-27T05:28:17.593840Z","task_state":""}
         * top : {"is_top":true,"top_time":"2018-01-27T05:28:17.656334Z"}
         */

        private String created_time;
        private CrowdBean crowd;
        private String describe;
        private String title;
        private String content;
        private String disease_type;
        private String doctor_address;
        private String doctor_type;
        private InitiatorBean initiator;
        private String origin_from;
        private int pk;
        private int praise;
        private TaskBean task;
        private TopBean top;
        private List<FollowersBean> followers;

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public CrowdBean getCrowd() {
            return crowd;
        }

        public void setCrowd(CrowdBean crowd) {
            this.crowd = crowd;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getDisease_type() {
            return disease_type;
        }

        public void setDisease_type(String disease_type) {
            this.disease_type = disease_type;
        }

        public String getDoctor_address() {
            return doctor_address;
        }

        public void setDoctor_address(String doctor_address) {
            this.doctor_address = doctor_address;
        }

        public String getDoctor_type() {
            return doctor_type;
        }

        public void setDoctor_type(String doctor_type) {
            this.doctor_type = doctor_type;
        }

        public InitiatorBean getInitiator() {
            return initiator;
        }

        public void setInitiator(InitiatorBean initiator) {
            this.initiator = initiator;
        }

        public String getOrigin_from() {
            return origin_from;
        }

        public void setOrigin_from(String origin_from) {
            this.origin_from = origin_from;
        }

        public int getPk() {
            return pk;
        }

        public void setPk(int pk) {
            this.pk = pk;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public TaskBean getTask() {
            return task;
        }

        public void setTask(TaskBean task) {
            this.task = task;
        }

        public TopBean getTop() {
            return top;
        }

        public void setTop(TopBean top) {
            this.top = top;
        }

        public List<FollowersBean> getFollowers() {
            return followers;
        }

        public void setFollowers(List<FollowersBean> followers) {
            this.followers = followers;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public static class CrowdBean {
            /**
             * crowd_funding : 45000
             * crowd_progress : 0
             * crowd_providers : [{"address":"北京","age":20,"name":"张三","pk":1,"telephone":"110"}]
             * pk : 18
             */

            private String crowd_funding;
            private String crowd_progress;
            private int pk;
            private List<CrowdProvidersBean> crowd_providers;

            public String getCrowd_funding() {
                return crowd_funding;
            }

            public void setCrowd_funding(String crowd_funding) {
                this.crowd_funding = crowd_funding;
            }

            public String getCrowd_progress() {
                return crowd_progress;
            }

            public void setCrowd_progress(String crowd_progress) {
                this.crowd_progress = crowd_progress;
            }

            public int getPk() {
                return pk;
            }

            public void setPk(int pk) {
                this.pk = pk;
            }

            public List<CrowdProvidersBean> getCrowd_providers() {
                return crowd_providers;
            }

            public void setCrowd_providers(List<CrowdProvidersBean> crowd_providers) {
                this.crowd_providers = crowd_providers;
            }

            public static class CrowdProvidersBean {
                /**
                 * address : 北京
                 * age : 20
                 * name : 张三
                 * pk : 1
                 * telephone : 110
                 */

                private String address;
                private int age;
                private String name;
                private int pk;
                private String telephone;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public int getAge() {
                    return age;
                }

                public void setAge(int age) {
                    this.age = age;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPk() {
                    return pk;
                }

                public void setPk(int pk) {
                    this.pk = pk;
                }

                public String getTelephone() {
                    return telephone;
                }

                public void setTelephone(String telephone) {
                    this.telephone = telephone;
                }
            }
        }

        public static class InitiatorBean {
            /**
             * address : 北京
             * age : 20
             * name : 张三
             * pk : 1
             * telephone : 110
             */

            private String address;
            private int age;
            private String name;
            private int pk;
            private String telephone;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPk() {
                return pk;
            }

            public void setPk(int pk) {
                this.pk = pk;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }
        }

        public static class TaskBean {
            /**
             * pk : 18
             * task_deadline : 5天
             * task_executors : {"address":"山西","age":20,"name":"赵柳","pk":6,"telephone":"155"}
             * task_outline : 去趟山西探老中医
             * task_start_time : 2018-01-27T05:28:17.593840Z
             * task_state :
             */

            private int pk;
            private String task_deadline;
            private TaskExecutorsBean task_executors;
            private String task_outline;
            private String task_start_time;
            private String task_state;

            public int getPk() {
                return pk;
            }

            public void setPk(int pk) {
                this.pk = pk;
            }

            public String getTask_deadline() {
                return task_deadline;
            }

            public void setTask_deadline(String task_deadline) {
                this.task_deadline = task_deadline;
            }

            public TaskExecutorsBean getTask_executors() {
                return task_executors;
            }

            public void setTask_executors(TaskExecutorsBean task_executors) {
                this.task_executors = task_executors;
            }

            public String getTask_outline() {
                return task_outline;
            }

            public void setTask_outline(String task_outline) {
                this.task_outline = task_outline;
            }

            public String getTask_start_time() {
                return task_start_time;
            }

            public void setTask_start_time(String task_start_time) {
                this.task_start_time = task_start_time;
            }

            public String getTask_state() {
                return task_state;
            }

            public void setTask_state(String task_state) {
                this.task_state = task_state;
            }

            public static class TaskExecutorsBean {
                /**
                 * address : 山西
                 * age : 20
                 * name : 赵柳
                 * pk : 6
                 * telephone : 155
                 */

                private String address;
                private int age;
                private String name;
                private int pk;
                private String telephone;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public int getAge() {
                    return age;
                }

                public void setAge(int age) {
                    this.age = age;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPk() {
                    return pk;
                }

                public void setPk(int pk) {
                    this.pk = pk;
                }

                public String getTelephone() {
                    return telephone;
                }

                public void setTelephone(String telephone) {
                    this.telephone = telephone;
                }
            }
        }

        public static class TopBean {
            /**
             * is_top : true
             * top_time : 2018-01-27T05:28:17.656334Z
             */

            private boolean is_top;
            private String top_time;

            public boolean isIs_top() {
                return is_top;
            }

            public void setIs_top(boolean is_top) {
                this.is_top = is_top;
            }

            public String getTop_time() {
                return top_time;
            }

            public void setTop_time(String top_time) {
                this.top_time = top_time;
            }
        }

        public static class FollowersBean {
            /**
             * address : 上海
             * age : 20
             * name : 李四
             * pk : 2
             * telephone : 120
             */

            private String address;
            private int age;
            private String name;
            private int pk;
            private String telephone;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getPk() {
                return pk;
            }

            public void setPk(int pk) {
                this.pk = pk;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }
        }
    }

    public static class TaskProgressBean {
        /**
         * created_time : 2018-01-30T05:01:06.072435Z
         * des : 今天看到了刘大第5555555555
         * pk : 11
         * task : {"pk":18,"task_deadline":"5天","task_executors":{"address":"山西","age":20,"name":"赵柳","pk":6,"telephone":"155"},"task_outline":"去趟山西探老中医","task_start_time":"2018-01-27T05:28:17.593840Z","task_state":""}
         * task_progress_time : 2018-01-30T05:01:06.072435Z
         * path : /media/image/edit.png
         */

        private String created_time;
        private String des;
        private int pk;
        private TaskBeanX task;
        private String task_progress_time;
        private String path;

        public String getCreated_time() {
            return created_time;
        }

        public void setCreated_time(String created_time) {
            this.created_time = created_time;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public int getPk() {
            return pk;
        }

        public void setPk(int pk) {
            this.pk = pk;
        }

        public TaskBeanX getTask() {
            return task;
        }

        public void setTask(TaskBeanX task) {
            this.task = task;
        }

        public String getTask_progress_time() {
            return task_progress_time;
        }

        public void setTask_progress_time(String task_progress_time) {
            this.task_progress_time = task_progress_time;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public static class TaskBeanX {
            /**
             * pk : 18
             * task_deadline : 5天
             * task_executors : {"address":"山西","age":20,"name":"赵柳","pk":6,"telephone":"155"}
             * task_outline : 去趟山西探老中医
             * task_start_time : 2018-01-27T05:28:17.593840Z
             * task_state :
             */

            private int pk;
            private String task_deadline;
            private TaskExecutorsBeanX task_executors;
            private String task_outline;
            private String task_start_time;
            private String task_state;

            public int getPk() {
                return pk;
            }

            public void setPk(int pk) {
                this.pk = pk;
            }

            public String getTask_deadline() {
                return task_deadline;
            }

            public void setTask_deadline(String task_deadline) {
                this.task_deadline = task_deadline;
            }

            public TaskExecutorsBeanX getTask_executors() {
                return task_executors;
            }

            public void setTask_executors(TaskExecutorsBeanX task_executors) {
                this.task_executors = task_executors;
            }

            public String getTask_outline() {
                return task_outline;
            }

            public void setTask_outline(String task_outline) {
                this.task_outline = task_outline;
            }

            public String getTask_start_time() {
                return task_start_time;
            }

            public void setTask_start_time(String task_start_time) {
                this.task_start_time = task_start_time;
            }

            public String getTask_state() {
                return task_state;
            }

            public void setTask_state(String task_state) {
                this.task_state = task_state;
            }

            public static class TaskExecutorsBeanX {
                /**
                 * address : 山西
                 * age : 20
                 * name : 赵柳
                 * pk : 6
                 * telephone : 155
                 */

                private String address;
                private int age;
                private String name;
                private int pk;
                private String telephone;

                public String getAddress() {
                    return address;
                }

                public void setAddress(String address) {
                    this.address = address;
                }

                public int getAge() {
                    return age;
                }

                public void setAge(int age) {
                    this.age = age;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getPk() {
                    return pk;
                }

                public void setPk(int pk) {
                    this.pk = pk;
                }

                public String getTelephone() {
                    return telephone;
                }

                public void setTelephone(String telephone) {
                    this.telephone = telephone;
                }
            }
        }
    }
}

package org.nddb.www.mvptest.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admin on 09/26/2017.
 */

public class Activitys
{


        @SerializedName("ID")
        @Expose
        private Integer iD;
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("DueDate")
        @Expose
        private String dueDate;
        @SerializedName("Completed")
        @Expose
        private Boolean completed;

        public Integer getID() {
            return iD;
        }

        public void setID(Integer iD) {
            this.iD = iD;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDueDate() {
            return dueDate;
        }

        public void setDueDate(String dueDate) {
            this.dueDate = dueDate;
        }

        public Boolean getCompleted() {
            return completed;
        }

        public void setCompleted(Boolean completed) {
            this.completed = completed;
        }
}

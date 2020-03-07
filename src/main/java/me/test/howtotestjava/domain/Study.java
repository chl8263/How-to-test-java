package me.test.howtotestjava.domain;

import me.test.howtotestjava.study.StudyStatus;

public class Study {

    private StudyStatus status;

    private int limit;

    private Member owner;

    public Study(int limit){
        this.status = StudyStatus.DRAFT;

        if(limit < 0){
            throw new IllegalArgumentException("Limit must more than 0");
        }
        this.limit = limit;
    }

    public StudyStatus getStatus() {
        return this.status;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Member getOwner() {
        return owner;
    }

    public void setOwner(Member owner) {
        this.owner = owner;
    }
}

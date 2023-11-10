package Homework7.part1;

import java.util.*;

public class RecruitmentSystem {
    public static void main(String[] args){
        JobSeeker s1 = new JobSeeker("s1");
        JobSeeker s2 = new JobSeeker("s2");
        JobSeeker s3 = new JobSeeker("s3");
        JobSeeker s4 = new JobSeeker("s4");
        JobSeeker s5 = new JobSeeker("s5");

        Job j1 = new Job("前端","北京");
        Job j2 = new Job("后端","北京");

        j1.add_company("c1");
        j1.add_subscriber(s1);
        j1.add_company("c2");
        j1.add_subscriber(s2);
        j1.add_company("c3");
        j2.add_subscriber(s3);
        j2.add_subscriber(s4);
        j2.add_subscriber(s5);
        j2.add_company("c4");
    }
}

interface Subscriber{
    void update(String company_name);
}

class JobSeeker implements Subscriber{
    private String name;
    private List<String> companies;

    public JobSeeker(String name) {
        this.name = name;
    }

    public void setCompanies(List<String> companies) {
        this.companies = new ArrayList<>(companies);

    }

    public String getName(){
        return this.name;
    }

    @Override
    public void update(String company_name) {
        this.companies.add(company_name);
        System.out.println(this.name + "收到通知：" + company_name + "发布了符合订阅的工作岗位。");
    }
}

class Job{
    private List<Subscriber> subscribers;
    private List<String> companies;
    private String job_type;
    private String workplace;

    public Job(String job_type, String workplace) {
        this.subscribers = new ArrayList<>();
        this.companies = new ArrayList<>();
        this.job_type = job_type;
        this.workplace = workplace;
    }

    public void add_subscriber(JobSeeker subscriber){
        this.subscribers.add(subscriber);
        System.out.println("求职者" +
                subscriber.getName() + "订阅了工作类型：" + this.job_type + "，工作地点：" + this.workplace);
        subscriber.setCompanies(this.companies);
        System.out.println("符合订阅工作的公司有：");
        for(String company : companies){
            System.out.println(company);
        }
    }

    public void add_company(String company_name){
        this.companies.add(company_name);
        for(Subscriber subscriber : this.subscribers){
            subscriber.update(company_name);
        }
    }
}

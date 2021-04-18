package com.junioroffers.infrastructure.offer.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OfferClient implements RemoteOfferClient{

    private String title;
    private String company;
    private String salary;
    private String offerUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getOfferUrl() {
        return offerUrl;
    }

    public void setOfferUrl(String offerUrl) {
        this.offerUrl = offerUrl;
    }

    @Override
    public String toString() {
        return "OfferClient{" +
                "title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", salary='" + salary + '\'' +
                ", offerUrl=" + offerUrl +
                '}';
    }

    @Override
    public void getOffers() {

    }
}

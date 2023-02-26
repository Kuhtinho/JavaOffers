package com.junioroffers.offer.domain;

public interface SampleOffer {

    default Offer cdqPolandOffer(){
        return new Offer("7b3e02b3-6b1a-4e75-bdad-cef5b279b074",
                "Software Engineer - Mobile(m/f/d)",
                "4k - 8k PLN",
                "https://nofluffjobs.com/pl/job/software-engineer-mobile-m-f-d-cybersource-poznan-entavdpn",
                "Cybersource");
    }

    default Offer cybersourceOffer(){
        return new Offer("24ee32b6-6b15-11eb-9439-0242ac130002",
                "Junior DevOps Engineer",
                "8k - 14k PLN",
                "https://nofluffjobs.com/pl/job/junior-devops-engineer-cdq-poland-wroclaw-gnymtxqd",
                "CDQ Poland");
    }
}

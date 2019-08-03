package com.qa.data;

public class Users {
    private String account_name;


    public Users() {
        super();
    }

    public Users(String account_name) {
        super();
        this.account_name = account_name;
    }

//    public Search(int pageSize,int getPageNum,String context,String device,String chainType){
//        super();
//        this.pageSize = pageSize;
//        this.getPageNum = getPageNum;
//        this.context = context;
//        this.device = device;
//        this.chainType = chainType;
//    }

    public String setAccount_name() {
        return account_name;
    }


    public String getAccount_name() {
        return account_name;
    }

}

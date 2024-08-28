package com.ohgiraffers.section02.dto;

public class MenuDTO {
    private String name;
    private int price;
    private int categoryCode;
    private String status;

    public MenuDTO() {
    }

    public MenuDTO(String name, int price, int categoryCode, String status) {
        this.name = name;
        this.price = price;
        this.categoryCode = categoryCode;
        this.status = status;
    }

    //필드에 입력받은 값 넣기
    public MenuDTO menuName(String name){
        this.name = name;
        return this;
    }

    public MenuDTO price(int price){
        if (price <=0){
            System.out.println("음수가 입력됨");
        }else{
            this.price = price;
        }
        return this;
    }
    public MenuDTO categoryCode(int code){
        this.categoryCode = code;
        return this;
    }

    public MenuDTO status(String status){
       if(status.equals("예") || status.equals("Y")|| status.equals("y")){
           this.status = "Y";
       } else if (status.equals("아니오")||status.equals("N")|| status.equals("n")) {
           this.status = "N";
       }
       return this;
    }


    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", categoryCode=" + categoryCode +
                ", status='" + status + '\'' +
                '}';
    }
}

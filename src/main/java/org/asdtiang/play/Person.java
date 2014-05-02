package org.asdtiang.play;

public class Person {
	private int number;
	private String result="";
	public Person(int number){
		this.number = number;
	}
	public Person(){
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}

package com.example.dkmh.controller;

import java.util.regex.Pattern;

public class Time {
	private String name;
	private int a ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Time(String name , int a) {
		super();
		this.name = name;
		this.a = a;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}
	public static void main(String[] args) {
		System.out.println(Pattern.matches("123*", "1234"));
	}

}

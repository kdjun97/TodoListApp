package com.todo;

import java.util.Scanner;

import com.todo.dao.TodoList;
import com.todo.menu.Menu;
import com.todo.service.TodoUtil;

public class TodoMain {
	
	public static void start() {
		Scanner sc = new Scanner(System.in);
		TodoList l = new TodoList();
		boolean isList = false;
		boolean quit = false;
		TodoUtil.loadList(l, "todolist.txt");		
		Menu.displaymenu();
		
		do {
			Menu.prompt();
			isList = false;
			String choices = sc.nextLine();
			String choice = choices.split(" ")[0];
			
			switch (choice) {
			case "help": // help 명령어 시 display menu 기능 호출
				Menu.displaymenu();
				break;

			case "add":
				TodoUtil.createItem(l);
				break;
			
			case "del":
				TodoUtil.deleteItem(l);
				break;
				
			case "edit":
				TodoUtil.updateItem(l);
				break;
				
			case "ls":
				TodoUtil.listAll(l);
				break;
				
			case "ls_cate":
				TodoUtil.listAllCategory(l);
				break;

			case "ls_name_asc":
				l.sortByName();
				isList = true;
				System.out.println("제목순으로 정렬완료! (오름차순)");
				break;

			case "ls_name_desc":
				l.sortByName();
				l.reverseList();
				System.out.println("제목순으로 정렬완료! (내림차순)");
				isList = true;
				break;
				
			case "ls_date":
				l.sortByDate();
				System.out.println("날짜순으로 정렬완료!");
				isList = true;
				break;
				
			case "ls_date_desc":
				l.sortByDate();
				l.reverseList();
				System.out.println("날짜순으로 정렬완료! (내림차순)");
				isList = true;
				break;
			
			case "find":
				TodoUtil.findKeyWord(l, choices.split(" ")[1]); // keyWord라고만 가정함 (하나의 단어)
				break;
				
			case "find_cate":
				TodoUtil.findCate(l, choices.split(" ")[1]); // keyWord라고만 가정함 (하나의 단어)
				break;	

			case "exit":
				quit = true;
				break;

			default:
				System.out.println("명령어 오류. [도움말 명령어: help]");
				break;
			}
			
			if(isList) l.listAll();
		} while (!quit);
		TodoUtil.saveList(l, "todolist.txt");
	}
}

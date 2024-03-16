package com.pickle.pickledemo;

import com.pickle.pickledemo.dao.UsersDAO;
import com.pickle.pickledemo.entity.Users;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

/*@SpringBootApplication(
		scanBasePackages =  {
				"com.pickledemo.pickledemo",
				"com.pickledemo.entity"
		}
)*/
@SpringBootApplication
public class PickleApplication {

    public static void main(String[] args) {
        SpringApplication.run(PickleApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(UsersDAO usersDAO) {
        return runner -> {
//			createUser(usersDAO);

//			Users userById = findUserById(usersDAO);
//			System.out.println(userById.toString());

			*//*List<Users> allUsers = findAllUsers(usersDAO);
			for (Users eachUser : allUsers) {
				System.out.println(eachUser.toString());
			}*//*
			*//*String name = "ismail";
			Users foundUser = findByFirstName(usersDAO, name);
			System.out.println(foundUser.toString());*//*

			*//*List<String> sortedUsers = sortByName(usersDAO);
			System.out.println(sortedUsers);*//*

			*//*int id1 = 4, id2 = 5;
			usersDAO.deleteById(id1);
			usersDAO.deleteById(id2);*//*

			*//*Users updatedUser = findUserById(usersDAO,1);
			updatedUser.setAge(33);
			usersDAO.updateUsers(updatedUser);*//*

//			System.out.println(usersDAO.findInclude("il"));

			*//*int id = 1;
			Users theUser = new Users("ismail", "özkan", "ismail@mail.com", 33);
			updateUserByUsingId(usersDAO, 1, theUser);*//*

			//cleanTable(usersDAO);


		};
    }*/

	private void cleanTable(UsersDAO usersDAO) {

		usersDAO.dropTable();

	}

	private void updateUserByUsingId(UsersDAO usersDAO, int id, Users theUser) {
		usersDAO.updateUserById(id, theUser);
	}

	private List<String> sortByName(UsersDAO usersDAO) {
		return usersDAO.sortByName();
	}

	private Users findByFirstName(UsersDAO usersDAO, String name) {
		return usersDAO.findByFirstName(name);
	}

	private List<Users> findAllUsers(UsersDAO usersDAO) {
		return usersDAO.findAll();
	}

	/*private Users findUserById(UsersDAO usersDAO,int id) {
		Users foundUser = usersDAO.findById(id);
		return foundUser;
	}*/

	private void createUser(UsersDAO usersDAO) {
		Users user2 = new Users("Nihad","Özkan","nihadaozkan@mail.com",67);
		Users user3 = new Users("Taha","Özkan","haticeozkan@mail.com",60);

		usersDAO.save(user2);
		usersDAO.save(user3);

	}

	private List<String> findIncludedUserNames(UsersDAO usersDAO, String str){
		return usersDAO.findInclude(str);
	}

}

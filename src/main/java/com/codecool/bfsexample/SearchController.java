package com.codecool.bfsexample;

import com.codecool.bfsexample.dao.UserDao;
import com.codecool.bfsexample.dao.UserDaoImpl;
import com.codecool.bfsexample.model.UserNode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;

public class SearchController {

    private UserDao dao;

    public SearchController(UserDao dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("bfsExampleUnitUpdate");
        EntityManager em = emf.createEntityManager();
        em.clear();
        UserDao dao = new UserDaoImpl(em);
        SearchController sc = new SearchController(dao);

        List<UserNode> users = sc.dao.getAllUsers();

        GraphPlotter.plot(users);

//        System.out.println("First user:");
//        UserNode firstUser = sc.getUserByInput();
//
//        System.out.println("Second user:");
//        UserNode secondUser = sc.getUserByInput();
//
//        System.out.print("Distance is equal: ");
//        System.out.println(sc.getMinDistanceBetweenUsers(firstUser, secondUser));

        System.out.println("User to find friends:");
        UserNode user = sc.getUserByInput();
        System.out.print("Depth: ");
        int depth = Integer.parseInt(new Scanner(System.in).nextLine());

        Set<UserNode> friends = sc.getFriendsByDepth(user, depth);
        System.out.println("Friends:");
        friends.forEach(System.out::println);
    }

    private UserNode getUserByInput() {
        UserNode user = null;
        while (user == null) {
            user = dao.getUserByFullName(new Scanner(System.in).nextLine());
        }
        return user;
    }

    public int getMinDistanceBetweenUsers(UserNode firstUser, UserNode secondUser) {

        BreadthFirstSearch bfs = new BreadthFirstSearch(firstUser);

        while (bfs.hasNext()) {
            if (bfs.next().equals(secondUser)) return bfs.getDepth();
        }

        return 0;
    }

    public Set<UserNode> getFriendsByDepth(UserNode user, int depth) {

        BreadthFirstSearch bfs = new BreadthFirstSearch(user);
        Set<UserNode> friends = new HashSet<>();

        while (bfs.hasNext()) {
            UserNode currentUser = bfs.next();
            if (bfs.getDepth() > depth) break;
            friends.add(currentUser);
        }

        friends.remove(user);

        return friends;
    }
}

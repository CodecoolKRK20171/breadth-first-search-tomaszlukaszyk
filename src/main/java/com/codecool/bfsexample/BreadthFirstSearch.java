package com.codecool.bfsexample;

import com.codecool.bfsexample.model.UserNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch {

    private UserNode emptyUser;
    private Queue<UserNode> queue;
    private Set<UserNode> visited;
    private int depth;

    public BreadthFirstSearch(UserNode user) {
        this.emptyUser = new UserNode();
        this.queue = new LinkedList<>();
        this.visited = new HashSet<>();
        this.queue.add(user);
        this.queue.add(this.emptyUser);
    }

    public Set<UserNode> getVisited() {
        return this.visited;
    }

    public int getDepth() {
        return this.depth;
    }

    public boolean hasNext() {
        return this.queue.size() > 1;
    }

    public UserNode next() {
        UserNode currentUser = getCurrentUser();
        this.visited.add(currentUser);
        Set<UserNode> friends = currentUser.getFriends();

        for (UserNode friend: friends) {
            if (!this.visited.contains(friend)) {
                this.queue.add(friend);
            }
        }

        return currentUser;
    }

    private UserNode getCurrentUser() {
        UserNode currentUser = this.queue.poll();

        if (currentUser == this.emptyUser) {
            this.depth++;
            this.queue.add(this.emptyUser);
            currentUser = this.queue.poll();
        }

        return currentUser;
    }
}

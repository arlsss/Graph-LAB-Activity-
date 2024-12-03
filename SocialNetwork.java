import java.util.*;

class SocialNetwork {
    // Map to store the adjacency list
    private Map<String, List<String>> network;

    // Constructor
    public SocialNetwork() {
        network = new HashMap<>();
    }

    // Add a user to the network
    public void addUser(String user) {
        network.putIfAbsent(user, new ArrayList<>());
    }

    // Add a friendship (edge) between two users
    public void addFriendship(String user1, String user2) {
        // Ensure both users exist in the network
        addUser(user1);
        addUser(user2);

        // Add each user to the other's friend list
        network.get(user1).add(user2);
        network.get(user2).add(user1);
    }

    // Get a list of friends for a user
    public List<String> getFriends(String user) {
        return network.getOrDefault(user, new ArrayList<>());
    }

    // Display the entire social network
    public void displayNetwork() {
        for (String user : network.keySet()) {
            System.out.println(user + " -> " + network.get(user));
        }
    }

    // Check if two users are directly connected
    public boolean areFriends(String user1, String user2) {
        return network.containsKey(user1) && network.get(user1).contains(user2);
    }

    // Find mutual friends between two users
    public List<String> findMutualFriends(String user1, String user2) {
        if (!network.containsKey(user1) || !network.containsKey(user2)) {
            return new ArrayList<>();
        }

        List<String> mutualFriends = new ArrayList<>(network.get(user1));
        mutualFriends.retainAll(network.get(user2));
        return mutualFriends;
    }

    public static void main(String[] args) {
        SocialNetwork sn = new SocialNetwork();

        // Add users and friendships
        sn.addFriendship("Alice", "Bob");
        sn.addFriendship("Alice", "Charlie");
        sn.addFriendship("Bob", "David");
        sn.addFriendship("Charlie", "David");
        sn.addFriendship("Charlie", "Eve");

        // Display the social network
        System.out.println("Social Network:");
        sn.displayNetwork();

        // Check friends
        System.out.println("\nFriends of Alice: " + sn.getFriends("Alice"));
        System.out.println("Are Alice and Bob friends? " + sn.areFriends("Alice", "Bob"));
        System.out.println("Are Alice and David friends? " + sn.areFriends("Alice", "David"));

        // Find mutual friends
        System.out.println("\nMutual friends of Bob and Charlie: " + sn.findMutualFriends("Bob", "Charlie"));
    }
}
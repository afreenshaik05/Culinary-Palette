import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CulinaryPalette {
    private static List<User> users = new ArrayList<>();
    private static List<Recipe> recipes = new ArrayList<>();
    private static User logUser = null;
    private static Scanner scanner = new Scanner(System.in);

    public void run(){
         boolean exit = false;
        while (!exit) {
            if (logUser == null) {
                WelcomeMenu();
            } else {
                MainMenu();
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    signUp();
                    break;
                case 2:
                    if (logUser == null) {
                        login();
                    } else {
                        System.out.println("You are already logged in.");
                    }
                    break;
                case 3:
                    if (logUser != null) {
                        submitRecipe();
                    } else {
                        System.out.println("Please log in to submit your Recipe.");
                    }
                    break;
                case 4:
                    listRecipes();
                    break;
                case 5:
                    if(logUser!=null){
                        likeRecipe();
                    }
                    else{
                        System.out.println("Please Log in to like the Recipes");
                    }
                    break;
                case 6:
                    if(logUser!=null){
                        commentOnRecipe();
                    }
                    else{
                        System.out.println("Please Log in to comment on your favorite recipes");
                    }
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println(" ");
        System.out.println("Thank you for using Culinary Palette! Happy Cooking ><");
        System.out.println(" ");
    }

    private static void WelcomeMenu() {
        System.out.println(" ");
        System.out.println("Welcome to Culinary Palette - The Ultimate Recipe Sharing Platform!");
        System.out.println("1. Sign Up");
        System.out.println("2. Login");
        System.out.println("3. Submit Recipe");
        System.out.println("4. List Total Recipes");
        System.out.println("5. Like a Recipe");
        System.out.println("6. Comment on a Recipe");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void  MainMenu() {
        System.out.println(" ");
        System.out.println("Welcome! " + logUser.getUsername() + " <3");
        System.out.println("3. Submit Recipe");
        System.out.println("4. List Recipes");
        System.out.println("5. Like");
        System.out.println("6. Comment");
        System.out.println("7. Logout");
        System.out.print("Enter your choice: ");
    }

    private static void login() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                logUser = user;
                System.out.println("Login successful!");
                return;
            }
        }
        System.out.println("Invalid username or password.");
    }

    private static void signUp() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        User newUser = new User(username, password);
        users.add(newUser);
        System.out.println("Sign up successful! Please login to continue");
    }

    private static void submitRecipe() {
        System.out.print("Enter Your Signature recipe name: ");
        String recipeName = scanner.nextLine();
        System.out.print("Enter the recipe description: ");
        String description = scanner.nextLine();

        Recipe newRecipe = new Recipe(logUser, recipeName, description);
        recipes.add(newRecipe);
        System.out.println("The Recipe has been submitted successfully!");
    }

    private static void listRecipes() {
        if (recipes.isEmpty()) {
            System.out.println(" ");
            System.out.println("Sorry! There are no Recipes. Please feel free to add one");
        } else {
            System.out.println("Recipes:");
            for (Recipe recipe : recipes) {
                System.out.println(recipe.getRecipeName() + " -by " + recipe.getOwner().getUsername());
                System.out.println("Description: " + recipe.getDescription());
            }
        }
    }
    private void likeRecipe() {
        System.out.println("Select recipe index to like:");
        listRecipes();

        int recipeIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (recipeIndex >= 0 && recipeIndex < recipes.size()) {
            Recipe recipe = recipes.get(recipeIndex);
            recipe.addLike();
            System.out.println("Thankyou for liking the recipe: " + recipe.getRecipeName());
        } else {
            System.out.println("Invalid recipe selection.");
        }
    }

    private void commentOnRecipe() {
        System.out.println("Select a recipe to comment on:");
        listRecipes();

        int recipeIndex = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        if (recipeIndex >= 0 && recipeIndex < recipes.size()) {
            Recipe recipe = recipes.get(recipeIndex);

            System.out.println("Enter your comment:");
            String comment = scanner.nextLine();
            recipe.addComment(comment);

            System.out.println("Thankyou for commenting. Your comment has been added to the recipe: " + recipe.getRecipeName());
        } else {
            System.out.println("Invalid recipe selection.");
        }
    }
      public static void main(String[] args) {
      CulinaryPalette platform=new CulinaryPalette();
      platform.run();

}
}
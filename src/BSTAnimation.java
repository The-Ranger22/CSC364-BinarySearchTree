import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.util.Iterator;

/*
    Author: Dr. Ward
 */

public class BSTAnimation extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BST<Integer> tree = new BST<>(); // Create a tree

        BorderPane pane = new BorderPane();
        BTView view = new BTView(tree); // Create a View
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        //--NEW BUTTONS--<_>
        Button btSearch = new Button("Search");
        Button btInorder = new Button("Inorder");
        Button btPreorder = new Button("Preorder");
        Button btPostorder = new Button("Postorder");
        Button btBreadthFirst = new Button("Breadth-first");
        Button btHeight = new Button("Height");
        //---------------<_>
        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete, btSearch, btInorder, btPreorder, btPostorder, btBreadthFirst, btHeight);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);
        //Event Listeners
        btInsert.setOnAction(e -> insert(tree, view, tfKey));
        btDelete.setOnAction(e -> delete(tree, view, tfKey));
        btSearch.setOnAction(e -> search(tree, view, tfKey));
        btInorder.setOnAction(e -> inorder(tree, view));
        btPreorder.setOnAction(e -> preorder(tree, view));
        btPostorder.setOnAction(e -> postorder(tree, view));
        btBreadthFirst.setOnAction(e -> breadthFirst(tree, view));
        btHeight.setOnAction(e -> height(tree, view));

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 450, 250);
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setMinHeight(240);
        primaryStage.setMinWidth(680);
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }
    //Moved all code related to the buttons into individual methods in order to keep things looking tidy
    void insert(BST<Integer> tree, BTView view, TextField tfKey){
        int key = Integer.parseInt(tfKey.getText());
        if (tree.search(key)) { // key is in the tree already
            view.displayTree();   // Clears the old status message
            view.setStatus(key + " is already in the tree");
        }
        else {
            tree.insert(key); // Insert a new key
            view.displayTree();
            view.setStatus(key + " is inserted in the tree");
        }
    }
    void delete(BST<Integer> tree, BTView view, TextField tfKey){
        int key = Integer.parseInt(tfKey.getText());
        if (!tree.search(key)) { // key is not in the tree
            view.displayTree();    // Clears the old status message
            view.setStatus(key + " is not in the tree");
        }
        else {
            Iterator<BST.TreeNode<Integer>> iter = view.getSearchPath().iterator();
            boolean inPath = false;
            while(iter.hasNext() && inPath == false){
                if(iter.next().element == key){
                    view.getSearchPath().clear();
                    inPath = true;
                }
            }
            tree.delete(key); // Delete a key
            view.displayTree();
            view.setStatus(key + " is deleted from the tree");
        }
    }
    void search(BST<Integer> tree, BTView view, TextField tfKey){
        int key = Integer.parseInt(tfKey.getText());
        if(!tree.search(key)){
            view.displayTree();
            view.setStatus(key + " is not in the tree");
        } else {
            view.setSearchPath(tree.path(key));
            view.displayTree();
            view.setStatus("Found " + key + " in the tree");

            Iterator<BST.TreeNode<Integer>> iter = view.getSearchPath().iterator();
            while(iter.hasNext()){
                System.out.println(iter.next().element);
            }
        }
    }
    void inorder(BST<Integer> tree, BTView view){
        view.displayTree();
        view.setStatus(tree.inorderList().toString());
    }
    void preorder(BST<Integer> tree, BTView view){
        view.displayTree();
        view.setStatus(tree.preorderList().toString());
    }
    void postorder(BST<Integer> tree, BTView view){
        view.displayTree();
        view.setStatus(tree.postorderList().toString());
    }
    void breadthFirst(BST<Integer> tree, BTView view){
        view.displayTree();
        view.setStatus(tree.breadthFirstOrderList().toString());
    }
    void height(BST<Integer> tree, BTView view){
        view.displayTree();
        view.setStatus("Tree height is " + tree.height());
    }



    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}


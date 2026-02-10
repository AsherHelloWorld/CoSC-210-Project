package model;

// Represents an object that can be searched using a keyword.
public interface Searchable {
    // EFFECTS: searches for the given keyword in this object.
    void search(String keyword);
}

package edu.kit.tm.cm.smartcampus.buildingmanagement.logic.model.utils;

/**
 * This record represents a datastructure, which describes a favored object.
 *
 * @param reference referenced object (of generic type)
 * @param owner string representing an user's e-mail address as unique identifier
 * @param <T> generic parameter (e.g., @Building or @Room)
 *
 * @author Bastian Bacher
 * @version 1.0
 */
public record Favorite<T>(T reference, String owner) {

}

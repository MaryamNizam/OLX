package olx;

import java.util.*;
import java.lang.*; 
import java.io.*; 

class Sortbyprice implements Comparator<Advertisement> 
{ 
    // Used for sorting in ascending order of 
    // price 
    public int compare(Advertisement a, Advertisement b) 
    { 
        return a.price - b.price; 
    } 
}

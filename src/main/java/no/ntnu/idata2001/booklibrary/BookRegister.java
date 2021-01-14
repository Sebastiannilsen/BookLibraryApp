package no.ntnu.idata2001.booklibrary;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Write a description of class no.ntnu.idata2001.booklibrary.BookRegister here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BookRegister{
   private final ArrayList<Book> books;

   // Makes a new arrayList to hold the books.
   public BookRegister(){
       this.books = new ArrayList<>();
   }

   //Fills the ArrayList with dummy books to test.
   public void fillWithBooks(){
       this.books.add(new Book("Død Snø","Jo Nesbø", "Gyldendal", 2014,365,"123456"));
       this.books.add(new Book("Sult","Knut Hamsun", "Canongate Books", 1890,195,"123457"));
       this.books.add(new Book("Doppler","Erlend Loe", "Cappelen Damm", 2004, 163, "123458"));
       this.books.add(new Book("En pingles dagbok","Jeff Kinney", "Gyldendal",  2007, 224, "123459"));
       this.books.add(new Book("Politi","Jo Nesbø", "Gyldendal",  2013, 653, "123454"));
   }
    //Method for adding a new book.
   public boolean addBook(Book book){
       if(null == book){
           return false;
        }else{
            this.books.add( book );
            return true;
        }
   }
   // Method for listing all books.
   public void listAllBooks(){
       for(Book book:books){
           System.out.println("\nTitle: " + book.getTitle()
                        + ", Author: " + book.getAuthor()
                        + ", Pages: " + book.getPages()
                        + ", Publisher: " + book.getPublisher()
                        + ", Barcode: " + book.getBarcode()
                        + ", Year Published: " + book.getYearPublished()
                        + ", Available: " + book.getStatus());
       }
   }
   
   public int getNumberOfBooks() {
    return this.books.size();
  }

    // A better method for listing all books.
   public ArrayList<Book> listAllBooks2(){
       ArrayList<Book> books = new ArrayList<>();
       Iterator<Book> it = this.books.iterator();
       if (!it.hasNext()) {
           System.out.println("There are no books in the register");
       } else{
           while (it.hasNext()){
               books.add(it.next());
           }
       }
        return books;
   }

   // Method for searching for title and returning the book you searched for.
   public Book getBookByTitle(String title){
       Book selectedBook = null;
       boolean stopSearching = false;
       Iterator<Book> iterator = books.iterator();
       while (iterator.hasNext() && !stopSearching){
           Book book = iterator.next();
           if (book.getTitle().equalsIgnoreCase(title)){
               selectedBook = book;
               stopSearching = true;
           }
       }
       return selectedBook;
   }



    // Method for searching for author and returning the book you searched for.
    public ArrayList<Book> getBookByAuthor(String author){
        Book selectedAuthor = null;
        ArrayList<Book> authors = new ArrayList<>();
        //boolean stopSearching = false;
        //Iterator<no.ntnu.idata2001.booklibrary.Book> iterator = books.iterator();
        for (int i = 0; i < books.size(); i++){
            Book book = books.get(i);
            if (book.getAuthor().equalsIgnoreCase(author)){
                selectedAuthor = book;
                authors.add(selectedAuthor);
                //stopSearching = true;
            }
        }
        return authors;
    }

    // Method for searching for barcode and returning the book you searched for:
    public Book getBookByBarcode(String barcode){
        Book selectedBarcode = null;
        boolean stopSearching = false;
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext() && !stopSearching){
            Book book = iterator.next();
            if (book.getBarcode().equals(barcode)){
                selectedBarcode = book;
                stopSearching = true;
            }
        }
        return selectedBarcode;
    }

    //Method for removing a book from the arrayList.
    public boolean removeBook(Book book){
        boolean success = false;
        if(null == book){return false;}
            
        else{
            this.books.remove(book);
            success = true;
        }
        return success;
    }

    //Get iterator method for calling the iterator.
    public Iterator<Book> getIterator(){
       return this.books.iterator();
    }

}

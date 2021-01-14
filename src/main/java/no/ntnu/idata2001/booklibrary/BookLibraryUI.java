package no.ntnu.idata2001.booklibrary;

import java.util.Scanner;
    import java.util.Iterator;
    import java.util.ArrayList;
    
    /**
     * Write a description of class no.ntnu.idata2001.booklibrary.BookLibraryUI here.
     *
     * @author (your name)
     * @version (a version number or a date)
     */
    public class BookLibraryUI{
        private final BookRegister books;
        private static final int ADD_BOOK = 1;
        private static final int LIST_ALL_BOOKS = 2;
        private static final int SEARCH_BOOK_BY_TITLE = 3;
        private static final int SEARCH_BOOK_BY_AUTHOR = 4;
        private static final int SEARCH_BOOK_BY_BARCODE = 5;
        private static final int REMOVE_BOOK = 6;
        private static final int QUIT = 7;
        
        public BookLibraryUI(){
            this.books = new BookRegister();
        }
        
        public void init(){
            this.books.fillWithBooks();
        }
        
        private int showMenu(){
            int menuChoice = 0;
            
            System.out.println("\n***** no.ntnu.idata2001.booklibrary.Book Library Application v0.1 *****\n\n");
            System.out.println("1. Add book");
            System.out.println("2. List all books");
            System.out.println("3. Search for book by title");
            System.out.println("4. Search for book by author");
            System.out.println("5. Search for book by barcode");
            System.out.println("6. Remove book");
            System.out.println("7. Quit");
            System.out.println("\nPlease enter a number between 1 and 7.\n");
            
            Scanner sc = new Scanner(System.in);
            if(sc.hasNextInt()){
                menuChoice = sc.nextInt();
            }else{
                System.out.println("You must enter a number");
            }
            return menuChoice;
        }
        
        public void start(){
            boolean finished = false;
            
            while (!finished){
                int menuChoice = this.showMenu();

                switch (menuChoice) {
                    case ADD_BOOK:
                        this.addBook();
                        break;
                    
                    case LIST_ALL_BOOKS:
                        this.listAllBooks();
                        break;
                        
                    case SEARCH_BOOK_BY_TITLE:
                        this.getBookByTitle();
                        break;
                        
                    case SEARCH_BOOK_BY_AUTHOR:
                        this.getBookByAuthor();
                        break;
                        
                    case SEARCH_BOOK_BY_BARCODE:
                        this.getBookByBarcode();
                        break;
                        
                    case REMOVE_BOOK:
                        this.removeBook();
                        break;
                        
                    case QUIT:
                        System.out.println("Thank you for using the book library app!\n");
                        finished = true;
                        break;
                    
                    default:
                        System.out.println("You have entered an invalid menu choice. Please try again.");
                    }
                }
            }
            
            private void addBook(){
                String title = "";
                String author = "";
                String publisher = "";
                int yearPublished = 0;
                int pages = 0;
                String barcode = "";
                
                Scanner reader = new Scanner(System.in);
                System.out.println("Add a new book.");
                
                do{
                    System.out.println("please enter title: ");
                    title = reader.nextLine();
                }while(!validateStringInput(title));    
                
                do{
                    System.out.println("please enter author: ");
                    author = reader.nextLine();
                }while(!validateStringInput(author));  
                
                do{
                    System.out.println("please enter publisher: ");
                    publisher = reader.nextLine();
                }while(!validateStringInput(publisher));

                System.out.println("Please enter year published: ");
                while(yearPublished <= 0){
                    yearPublished = this.getInt();
                    if (yearPublished < 1){
                        System.out.println("You must enter a number more than 0.");
                    }

                }

                System.out.println("Please enter pages: ");
                while(pages <= 0){
                    pages = this.getInt();
                    if (pages < 1){
                        System.out.println("You must enter a number more than 0.");
                    }

                }
        
                do{
                    System.out.println("please enter barcode: ");
                    barcode = reader.nextLine();
                }while(!validateStringInput(barcode)); 
                
                Book book = new Book(title, author, publisher, yearPublished, pages, barcode);
                this.books.addBook(book);
                System.out.println("The book with title " + book.getTitle()
                + " has been added to the register.");
                
            }
        
        private void listAllBooks(){
            Iterator<Book> it = this.books.getIterator();
            
            System.out.printf("%-40s  %-20s  %-20s  %-4s  %-5s  %-13s  %-4s%n",
            "Title", "Author", "Publisher", "Year", "Pages", "Barcode", "Available");

            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
            
            while (it.hasNext()){
                Book book = it.next();
                
                String borrowedString;
                if(!book.available){
                    borrowedString = "No";
                }else{
                    borrowedString = "Yes";
                }
                
                System.out.printf("%-40s  %-20s  %-20s  %-4s  %-5s  %-13s  %-4s%n",
                book.getTitle(), book.getAuthor(), book.getPublisher(), 
                book.getYearPublished(), book.getPages(),
                book.getBarcode(), borrowedString);
            }
            System.out.println("---------------------------------------------------------------------------------------------------------------------------");
        }
        
        private void getBookByTitle(){
            Scanner reader = new Scanner(System.in);
            System.out.println("Search book by title");
            
            System.out.println("Please search for a book title:");
            
            String title = reader.nextLine();
            
            if(title.isEmpty()){
                System.out.println("Please enter a title to search for");
            }else{
                Book foundBook = this.books.getBookByTitle(title);
                if(null == foundBook){
                    System.out.println("No book was found with that title");
                }else{
                    System.out.println("Found book with following title: " + title);
                    this.displayBookDetails(foundBook);
                }
            }
        }
        
        private void getBookByAuthor(){
            Scanner reader = new Scanner(System.in);
            System.out.println("Search book by author");
            
            System.out.println("Please search for a book author:");
            
            String author = reader.nextLine();
            
            if(author.isEmpty()){
                System.out.println("Please enter a author to search for");
            }else{
                ArrayList<Book> foundBooks = this.books.getBookByAuthor(author);
                if(null == foundBooks){
                    System.out.println("No book was found by that author");
                }else {
                    System.out.println("Found book with the following author: " + author);
                    for(int i = 0; i < foundBooks.size(); i++){
                        this.displayBookDetails(foundBooks.get(i));
                    }
                }
            }
        }
        
        private void getBookByBarcode(){
            Scanner reader = new Scanner(System.in);
            System.out.println("Search book by barcode");
            
            System.out.println("Please search for a book barcode:");
            
            String barcode = reader.nextLine();
            
            if(barcode.isEmpty()){
                System.out.println("Please enter a barcode to search for");
            }else{
                Book foundBook = this.books.getBookByBarcode(barcode);
                if(null == foundBook){
                    System.out.println("No book was found with that barcode");
                }else{
                    System.out.println("Found book with the following barcode: " + barcode);
                    this.displayBookDetails(foundBook);
                }
            }
        }
        
        private void removeBook(){
            Scanner reader = new Scanner(System.in);
            System.out.println("Delete book");
            
            System.out.println("Please enter the book you want to remove");
            
            String title = reader.nextLine();
            
            Book book = this.books.getBookByTitle(title);
            
            if(books.removeBook(book)){
                System.out.println("no.ntnu.idata2001.booklibrary.Book with title: " +title+ " was removed.");
            }else{
                System.out.println("No books where found with that title. No books removed.");
            } 
        }


        private boolean validateIntInput(int value, int min, int max){
            boolean validInt = false;
            if((value > max) || (value < min)){
                System.out.println("The value must be between "
                        + min + " and " + max + ". Please try again...");
            }else{
                validInt = true;
            }
        return validInt;
        }

        private int getInt(){
            int num = 0;
            boolean finished = false;
            while(!finished){
                Scanner input = new Scanner(System.in);
                if (input.hasNextInt()){
                    num = input.nextInt();
                    finished = true;
                }else{
                    System.out.println("Please enter a valid number.");
                }
            }
            return num;
        }

        
        private boolean validateStringInput(String inputString){
            boolean validString = false;
            if(inputString.trim().length() == 0){
                System.out.println("Text cannot be empty. Please try again...");
            }else{
                validString = true;
            }
            return validString;
        }
        
        private void displayBookDetails(Book book) {
            System.out.println("----------------------------------------------");
            System.out.println("Title           : " + book.getTitle());
            System.out.println("Author          : " + book.getAuthor());
            System.out.println("Publisher       : " + book.getPublisher());
            System.out.println("Year published  : " + book.getYearPublished());
            System.out.println("Number of pages : " + book.getPages());
            System.out.println("Barcode         : " + book.getBarcode());
            System.out.print(  "Available       : ");
            if (!book.available) {
                System.out.println("No");
            } else {
                System.out.println("Yes");
            }
            System.out.println("----------------------------------------------");
        }
}

package Practical5

case class Book(title: String, author: String, isbn: String)

object Library {
  var library: Set[Book] = Set(
    Book("1984", "George Orwell", "1234567890"),
    Book("To Kill a Mockingbird", "Harper Lee", "1234567891"),
    Book("The Great Gatsby", "F. Scott Fitzgerald", "1234567892")
  )

  def addBook(book: Book): Unit = {
    library += book
    println(s"Book '${book.title}' by ${book.author} added to the library.")
  }

  def removeBookByISBN(isbn: String): Unit = {
    library.find(_.isbn == isbn) match {
      case Some(book) =>
        library -= book
        println(s"Book '${book.title}' by ${book.author} removed from the library.")
      case None =>
        println(s"No book found with ISBN: $isbn")
    }
  }

  def isBookInLibrary(isbn: String): Boolean = {
    library.exists(_.isbn == isbn)
  }

  def displayLibrary(): Unit = {
    if (library.isEmpty) {
      println("The library is currently empty.")
    } else {
      println("Current library collection:")
      library.foreach { book =>
        println(s"Title: ${book.title}, Author: ${book.author}, ISBN: ${book.isbn}")
      }
    }
  }

  def searchBookByTitle(title: String): Unit = {
    library.find(_.title.equalsIgnoreCase(title)) match {
      case Some(book) =>
        println(s"Book found: Title: ${book.title}, Author: ${book.author}, ISBN: ${book.isbn}")
      case None =>
        println(s"No book found with title: $title")
    }
  }

  def displayBooksByAuthor(author: String): Unit = {
    val booksByAuthor = library.filter(_.author.equalsIgnoreCase(author))
    if (booksByAuthor.isEmpty) {
      println(s"No books found by author: $author")
    } else {
      println(s"Books by $author:")
      booksByAuthor.foreach { book =>
        println(s"Title: ${book.title}, ISBN: ${book.isbn}")
      }
    }
  }

  def main(args: Array[String]): Unit = {
    displayLibrary()
    println()

    val newBook = Book("Brave New World", "Aldous Huxley", "1234567893")
    addBook(newBook)
    displayLibrary()
    println()

    val isbnToRemove = "1234567891"
    removeBookByISBN(isbnToRemove)
    displayLibrary()
    println()

    val isbnToCheck = "1234567890"
    println(s"Is book with ISBN $isbnToCheck in library? ${isBookInLibrary(isbnToCheck)}")
    println()

    val titleToSearch = "1984"
    searchBookByTitle(titleToSearch)
    println()

    val authorToSearch = "George Orwell"
    displayBooksByAuthor(authorToSearch)
    println()
  }
}


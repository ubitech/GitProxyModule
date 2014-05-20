package eu.ubitech.ubicropper.gitproxy.exception;

/**
 *
 * @author pgouvas
 */
class GitNonInitializedException extends Exception {
      //Parameterless Constructor
      public GitNonInitializedException() {}

      //Constructor that accepts a message
      public GitNonInitializedException(String message)
      {
         super(message);
      }
 }
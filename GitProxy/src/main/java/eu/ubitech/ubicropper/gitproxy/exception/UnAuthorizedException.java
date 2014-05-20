package eu.ubitech.ubicropper.gitproxy.exception;

/**
 *
 * @author pgouvas
 */
class UnAuthorizedException extends Exception {
      //Parameterless Constructor
      public UnAuthorizedException() {}

      //Constructor that accepts a message
      public UnAuthorizedException(String message)
      {
         super(message);
      }
 }
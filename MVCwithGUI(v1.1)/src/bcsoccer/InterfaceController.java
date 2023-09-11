package bcsoccer;

/**
 * Represents a Controller for soccer team management system: handle couch input in model; convey
 * generated outcomes to the couch in some form.
 */
public interface InterfaceController {
  /**
   * Execute a single management system run. When the couch quit, the go method ends.
   */
  void go();
}
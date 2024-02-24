//package ac.ke.rondavels.marverick.errors;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
//import org.springframework.web.servlet.ModelAndView;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
//    public ModelAndView handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
//        return new ModelAndView("redirect:/400/");
//    }
//
//    @ExceptionHandler(RuntimeException.class)
//    public ModelAndView handleRuntimeException(RuntimeException ex) {
//        return new ModelAndView("redirect:/404/");
//    }
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> handleException(Exception ex) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
//    }
//}
//

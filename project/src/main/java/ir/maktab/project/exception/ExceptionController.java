package ir.maktab.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = ExamNotFoundException.class)
    public ResponseEntity<String> getExamNotFoundError(ExamNotFoundException e){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

    }
    @ExceptionHandler(value = NullPointerException.class)
    public ResponseEntity<String> getNullPointerExceptionError(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("you must fill data  it is mandatory");
    }
    @ExceptionHandler(value = StudentNotFoundException.class)
    public ResponseEntity<String> getStudentNotFoundError(StudentNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}

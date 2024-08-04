USE library;

DROP procedure IF EXISTS `update_educator`;
CREATE PROCEDURE `update_educator` (	@UserID           INT(10),
                                          @FirstName    VARCHAR(50),
                                          @LastName     VARCHAR(50),
                                          @UserPassword VARCHAR(50),
                                          @UserEmail   VARCHAR(100),
                                          @SchoolID	    INT(15),
                                          @CourseID	    INT(15)
                                          )

  BEGIN
     
            UPDATE employee
            SET    FirstName = @FirstName,
                   LastName = @LastName,
                   UserPassword = @UserPassword,
                   UserEmail = @UserEmail,
                   SchoolID = @SchoolID,
                   CourseID = @CourseID
            WHERE  UserID = @UserID 
       END

DELIMITER ;
       
      


package com.app.server.repository.appbasicsetup.usermanagement;
import com.app.server.repository.core.SearchInterface;
import com.spartan.server.interfaces.LoginSessionRepositoryInterface;
import com.app.config.annotation.Complexity;
import com.app.config.annotation.SourceCodeAuthorClass;

@SourceCodeAuthorClass(createdBy = "shubhangivhanale@gmail.com", updatedBy = "shubhangivhanale@gmail.com", versionNumber = "2", comments = "Repository for LoginSession Transaction table", complexity = Complexity.MEDIUM)
public interface LoginSessionRepository<T> extends SearchInterface, LoginSessionRepositoryInterface {
}

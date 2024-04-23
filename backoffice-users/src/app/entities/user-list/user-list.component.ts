import { Component } from '@angular/core';
import { User } from '../user/model/user.model';
import { UserService } from '../user/service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent {
  users: User[] = [];

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  private getUsers(): void {
    this.userService.getAllUsers().subscribe({
      next: (usersRequest) => {
        this.users = usersRequest;
      },
      error: (err) => {this.handleError(err);}
    });
  }

  private handleError(err: any): void {
    console.log(err);
  }

}

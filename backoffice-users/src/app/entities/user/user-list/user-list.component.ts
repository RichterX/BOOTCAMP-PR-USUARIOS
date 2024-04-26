import { Component } from '@angular/core';
import { User } from '../model/user.model';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent {
  users: User[] = [];

  // Paginación
  page: number = 0;
  size: number = 5;
  sort: string = "name,asc";

  first: boolean = false;
  last: boolean = false;
  totalPages: number = 0;
  totalElements: number = 0;

  //Filtro de formulario
  nameFilter?: string;
  surnameFilter?: string;
  roleFilter?: string;

  //Para el borrado de usuarios
  userIdToDelete?: number;

  public elseBlock: any;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUsers();
  }

  //Métodos de borrar usuario
  //Método que primero obtiene y guarda el id del usuario a borrar
  public prepareItemToDelete(id: number): void {
    this.userIdToDelete = id;
  }

  //Método para borrar el usuario con el id obtenido anteriormente.
  public deleteUser(): void {
    if (this.userIdToDelete)
    {
      this.userService.deleteUser(this.userIdToDelete).subscribe(
        {
          next: (data) => {
            this.getUsers();
          },
          error: (err) => {
            this.handleError(err);
          }
        }
      );
    }
  }

  //Métodos de paginación (página siguiente y anterior)
  public nextPage(): void {
    this.page++;
    this.getUsers();
  }

  public previousPage(): void {
    this.page--;
    this.getUsers();
  }

  //Filtrar y actualizar el listado
  public searchByFilter(): void {
    this.getUsers();
  }

  //Resetear el filtro
  public resetFilter(): void {
    this.nameFilter = undefined;
    this.surnameFilter = undefined;
    this.roleFilter = undefined;
    this.getUsers();
  }

  //Constructor de filtros
  private buildFilters(): string | undefined {
    const filters: string[] = [];
    if (this.nameFilter)
      {
        filters.push("name="+this.nameFilter);
      }
    if (this.surnameFilter)
      {
        filters.push("surname="+this.surnameFilter);
      }

    if (this.roleFilter)
      {
        filters.push("role="+this.roleFilter);
      }

    if (filters.length>0)
      {
        let globalFilters: string = "";
        for(let filter of filters)
          {
            globalFilters = globalFilters + filter + "&"; //Importante, con una coma evidentemente no funciona. Los filtros se separan con &, las comas es para el sort
          }

          globalFilters = globalFilters.substring(0, globalFilters.length-1);
          return globalFilters;
      } else {
        return undefined;
      }
  }

  //Obtenemos todos los usuarios del backend
  private getUsers(): void {

    const filters: string | undefined = this.buildFilters();

    this.userService.getAllUsersPaginated(this.page, this.size, this.sort, filters).subscribe({
      next: (data: any) => {
        this.users = data.content;
        this.first = data.first;
        this.last = data.last;
        this.totalPages = data.totalPages;
        this.totalElements = data.totalElements;
      },
      error: (err) => {this.handleError(err);}
    });
  }

  //Método para gestionar los errores
  private handleError(err: any): void {
    console.log(err);
  }




}

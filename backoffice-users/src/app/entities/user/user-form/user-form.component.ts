import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { User } from '../model/user.model';


@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.scss']
})
export class UserFormComponent {
  mode: "NEW" | "UPDATE" = "NEW";
  userId?: number = 0;
  user ?: User;

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private router: Router) {}

  ngOnInit(): void {
    const entryParam: string = this.route.snapshot.paramMap.get('userId') ?? 'new';

    //Si hay un id en la url, lo guardamos y editamos. Si no, es un usuario nuevo.
    if(entryParam !== 'new') {
      this.userId = Number(this.route.snapshot.paramMap.get('userId')); //OJO! El orden es importante
      this.mode = "UPDATE";
      this.getUserById(this.userId!);
    } else {
      this.mode = "NEW";
      this.initializeUser();
    }
  }

  //Método para obtener un usuario por su id
  private getUserById(userId: number) {
    this.userService.getUserById(userId).subscribe({
      next: (userRequested) => {
        this.user = userRequested;
      },
      error: (err) => {
        this.handleError(err);
      }
    })
  }

   //Método para gestionar los errores
   private handleError(err: any): void {
    console.log(err);
  }

  //Método para inicializar un nuevo usuario
  private initializeUser(): void {
    this.user = new User(undefined, '', '', '', '');
  }

  //Método para guardar o actualizar un usuario según el modo en el que estemos
  public saveUser(): void {
    if(this.mode === "NEW") {
      this.insertUser();
      //Volvemos a la pantalla principal
        this.router.navigate(['/users']);
    } else {
      this.updateUser();
      //Volvemos a la pantalla principal
      this.router.navigate(['/users']);
    }
  }

  //Método para insertar un nuevo usuario
  private insertUser(): void {
    this.userService.createUser(this.user!).subscribe({
      next: (user) => {
        this.user = user;
        console.log("Usuario insertado correctamente.");
      },
      error: (err) => {
        this.handleError(err);
      }
    });
  }

  //Método para actualizar un usuario
  private updateUser(): void {
    this.userService.updateUser(this.user!).subscribe({
      next: (UserUpdated) => {
        console.log("Datos actualizados correctamente.");
        console.log(UserUpdated);
      },
      error: (err) => {
        console.log("No es posible actualizar el usuario.");
        this.handleError(err);
      }
    });
  }
  

}

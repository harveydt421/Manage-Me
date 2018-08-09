import { IUser } from 'app/core/user/user.model';
import { IAsset } from 'app/shared/model//asset.model';

export interface IEmployee {
    id?: number;
    phoneNumber?: string;
    user?: IUser;
    asset?: IAsset;
}

export class Employee implements IEmployee {
    constructor(public id?: number, public phoneNumber?: string, public user?: IUser, public asset?: IAsset) {}
}

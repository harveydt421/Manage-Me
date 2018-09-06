import { IAsset } from 'app/shared/model//asset.model';
import { INotification } from 'app/shared/model//notification.model';

export interface IEmployee {
    id?: number;
    name?: string;
    phoneNumber?: string;
    userId?: number;
    assets?: IAsset[];
    notifications?: INotification[];
    separationApplicationId?: number;
    departmentName?: string;
    departmentId?: number;
}

export class Employee implements IEmployee {
    constructor(
        public id?: number,
        public phoneNumber?: string,
        public name?: string,
        public userId?: number,
        public assets?: IAsset[],
        public notifications?: INotification[],
        public separationApplicationId?: number,
        public departmentName?: string,
        public departmentId?: number
    ) {}
}

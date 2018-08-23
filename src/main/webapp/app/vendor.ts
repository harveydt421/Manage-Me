/* after changing this file run 'yarn run webpack:build' */
/* tslint:disable */
import '../content/scss/vendor.scss';

// Imports all fontawesome core and solid icons

import { library, dom } from '@fortawesome/fontawesome-svg-core';
import { NgModule } from '@angular/core';

import {
    faUser,
    faSort,
    faSync,
    faEye,
    faBan,
    faTimes,
    faArrowLeft,
    faSave,
    faPlus,
    faPencilAlt,
    faBars,
    faThList,
    faUserPlus,
    faRoad,
    faTachometerAlt,
    faHeart,
    faList,
    faBell,
    faBook,
    faHdd,
    faFlag,
    faWrench,
    faClock,
    faCloud,
    faSignOutAlt,
    faSignInAlt,
    faCalendarAlt,
    faSearch,
    faTrashAlt,
    faAsterisk,
    faTasks,
    faHome,
    faMinusCircle,
    faUsers,
    faLaptop,
    faGlobeAmericas,
    faCheck
} from '@fortawesome/free-solid-svg-icons';

import { faBell as farBell, faFile as farFile, faUser as farUser, faBuilding as farBuilding } from '@fortawesome/free-regular-svg-icons';

// Adds the SVG icon to the library so you can use it in your page
library.add(faUser);
library.add(faSort);
library.add(faSync);
library.add(faEye);
library.add(faBan);
library.add(faTimes);
library.add(faArrowLeft);
library.add(faSave);
library.add(faPlus);
library.add(faPencilAlt);
library.add(faBars);
library.add(faHome);
library.add(faThList);
library.add(faUserPlus);
library.add(faRoad);
library.add(faTachometerAlt);
library.add(faHeart);
library.add(faList);
library.add(faBell);
library.add(faTasks);
library.add(faBook);
library.add(faHdd);
library.add(faFlag);
library.add(faWrench);
library.add(faClock);
library.add(faCloud);
library.add(faSignOutAlt);
library.add(faSignInAlt);
library.add(faCalendarAlt);
library.add(faSearch);
library.add(faTrashAlt);
library.add(faAsterisk);
library.add(faMinusCircle);
library.add(farBell);
library.add(farFile);
library.add(farUser);
library.add(faUsers);
library.add(faLaptop);
library.add(farBuilding);
library.add(faGlobeAmericas);
library.add(faCheck);

dom.watch();

// jhipster-needle-add-element-to-vendor - JHipster will add new menu items here

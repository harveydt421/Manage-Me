/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { ManageMeTestModule } from '../../../test.module';
import { SeparationApplicationDeleteDialogComponent } from 'app/entities/separation-application/separation-application-delete-dialog.component';
import { SeparationApplicationService } from 'app/entities/separation-application/separation-application.service';

describe('Component Tests', () => {
    describe('SeparationApplication Management Delete Component', () => {
        let comp: SeparationApplicationDeleteDialogComponent;
        let fixture: ComponentFixture<SeparationApplicationDeleteDialogComponent>;
        let service: SeparationApplicationService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ManageMeTestModule],
                declarations: [SeparationApplicationDeleteDialogComponent]
            })
                .overrideTemplate(SeparationApplicationDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SeparationApplicationDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SeparationApplicationService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});

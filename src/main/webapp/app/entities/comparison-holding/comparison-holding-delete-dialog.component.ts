import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IComparisonHolding } from 'app/shared/model/comparison-holding.model';
import { ComparisonHoldingService } from './comparison-holding.service';

@Component({
    selector: 'jhi-comparison-holding-delete-dialog',
    templateUrl: './comparison-holding-delete-dialog.component.html'
})
export class ComparisonHoldingDeleteDialogComponent {
    comparisonHolding: IComparisonHolding;

    constructor(
        protected comparisonHoldingService: ComparisonHoldingService,
        public activeModal: NgbActiveModal,
        protected eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.comparisonHoldingService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'comparisonHoldingListModification',
                content: 'Deleted an comparisonHolding'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-comparison-holding-delete-popup',
    template: ''
})
export class ComparisonHoldingDeletePopupComponent implements OnInit, OnDestroy {
    protected ngbModalRef: NgbModalRef;

    constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ comparisonHolding }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(ComparisonHoldingDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.comparisonHolding = comparisonHolding;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate(['/comparison-holding', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate(['/comparison-holding', { outlets: { popup: null } }]);
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}

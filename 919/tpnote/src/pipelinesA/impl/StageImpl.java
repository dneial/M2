/**
 */
package pipelinesA.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import pipelinesA.Job;
import pipelinesA.PipelinesAPackage;
import pipelinesA.Stage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pipelinesA.impl.StageImpl#getJobs <em>Jobs</em>}</li>
 *   <li>{@link pipelinesA.impl.StageImpl#getNextStage <em>Next Stage</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StageImpl extends NamedElementImpl implements Stage {
	/**
	 * The cached value of the '{@link #getJobs() <em>Jobs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJobs()
	 * @generated
	 * @ordered
	 */
	protected EList<Job> jobs;

	/**
	 * The cached value of the '{@link #getNextStage() <em>Next Stage</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNextStage()
	 * @generated
	 * @ordered
	 */
	protected Stage nextStage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PipelinesAPackage.Literals.STAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Job> getJobs() {
		if (jobs == null) {
			jobs = new EObjectContainmentEList<Job>(Job.class, this, PipelinesAPackage.STAGE__JOBS);
		}
		return jobs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stage getNextStage() {
		return nextStage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNextStage(Stage newNextStage, NotificationChain msgs) {
		Stage oldNextStage = nextStage;
		nextStage = newNextStage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PipelinesAPackage.STAGE__NEXT_STAGE, oldNextStage, newNextStage);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNextStage(Stage newNextStage) {
		if (newNextStage != nextStage) {
			NotificationChain msgs = null;
			if (nextStage != null)
				msgs = ((InternalEObject)nextStage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PipelinesAPackage.STAGE__NEXT_STAGE, null, msgs);
			if (newNextStage != null)
				msgs = ((InternalEObject)newNextStage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PipelinesAPackage.STAGE__NEXT_STAGE, null, msgs);
			msgs = basicSetNextStage(newNextStage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PipelinesAPackage.STAGE__NEXT_STAGE, newNextStage, newNextStage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PipelinesAPackage.STAGE__JOBS:
				return ((InternalEList<?>)getJobs()).basicRemove(otherEnd, msgs);
			case PipelinesAPackage.STAGE__NEXT_STAGE:
				return basicSetNextStage(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PipelinesAPackage.STAGE__JOBS:
				return getJobs();
			case PipelinesAPackage.STAGE__NEXT_STAGE:
				return getNextStage();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PipelinesAPackage.STAGE__JOBS:
				getJobs().clear();
				getJobs().addAll((Collection<? extends Job>)newValue);
				return;
			case PipelinesAPackage.STAGE__NEXT_STAGE:
				setNextStage((Stage)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case PipelinesAPackage.STAGE__JOBS:
				getJobs().clear();
				return;
			case PipelinesAPackage.STAGE__NEXT_STAGE:
				setNextStage((Stage)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case PipelinesAPackage.STAGE__JOBS:
				return jobs != null && !jobs.isEmpty();
			case PipelinesAPackage.STAGE__NEXT_STAGE:
				return nextStage != null;
		}
		return super.eIsSet(featureID);
	}

} //StageImpl

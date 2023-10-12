/**
 */
package pipelinesA.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import pipelinesA.Pipeline;
import pipelinesA.PipelinesAPackage;
import pipelinesA.Stage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pipeline</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link pipelinesA.impl.PipelineImpl#getFirstStage <em>First Stage</em>}</li>
 * </ul>
 *
 * @generated
 */
public class PipelineImpl extends MinimalEObjectImpl.Container implements Pipeline {
	/**
	 * The cached value of the '{@link #getFirstStage() <em>First Stage</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFirstStage()
	 * @generated
	 * @ordered
	 */
	protected Stage firstStage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PipelineImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PipelinesAPackage.Literals.PIPELINE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Stage getFirstStage() {
		return firstStage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFirstStage(Stage newFirstStage, NotificationChain msgs) {
		Stage oldFirstStage = firstStage;
		firstStage = newFirstStage;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PipelinesAPackage.PIPELINE__FIRST_STAGE, oldFirstStage, newFirstStage);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFirstStage(Stage newFirstStage) {
		if (newFirstStage != firstStage) {
			NotificationChain msgs = null;
			if (firstStage != null)
				msgs = ((InternalEObject)firstStage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PipelinesAPackage.PIPELINE__FIRST_STAGE, null, msgs);
			if (newFirstStage != null)
				msgs = ((InternalEObject)newFirstStage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PipelinesAPackage.PIPELINE__FIRST_STAGE, null, msgs);
			msgs = basicSetFirstStage(newFirstStage, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PipelinesAPackage.PIPELINE__FIRST_STAGE, newFirstStage, newFirstStage));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PipelinesAPackage.PIPELINE__FIRST_STAGE:
				return basicSetFirstStage(null, msgs);
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
			case PipelinesAPackage.PIPELINE__FIRST_STAGE:
				return getFirstStage();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PipelinesAPackage.PIPELINE__FIRST_STAGE:
				setFirstStage((Stage)newValue);
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
			case PipelinesAPackage.PIPELINE__FIRST_STAGE:
				setFirstStage((Stage)null);
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
			case PipelinesAPackage.PIPELINE__FIRST_STAGE:
				return firstStage != null;
		}
		return super.eIsSet(featureID);
	}

} //PipelineImpl

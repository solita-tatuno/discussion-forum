import { ReactNode } from "react";
import { createPortal } from "react-dom";

interface Props {
  children: ReactNode;
  isOpen: boolean;
  onClose: () => void;
}

function Modal({ isOpen, onClose, children }: Props) {
  if (!isOpen) return null;

  return createPortal(
    <div className="fixed inset-0 bg-gray-600 bg-opacity-20 z-50 flex justify-center items-center">
      <div className="bg-white p-4 rounded-lg max-w-sm w-full">
        <button
          className="float-right top-0 right-0 text-xl font-semibold"
          onClick={onClose}
        >
          &times;
        </button>
        {children}
      </div>
    </div>,
    document.body,
  );
}

export default Modal;